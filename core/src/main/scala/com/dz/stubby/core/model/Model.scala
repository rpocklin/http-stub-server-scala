package com.dz.stubby.core.model

case class StubParam(
  name: String,
  value: String)

trait StubHeaders[T <: StubHeaders[T]] {
  val headers: List[StubParam]

  def withHeaders(headers: List[StubParam]): T

  def getHeader(name: String): Option[String] =
    headers.find(_.name.equalsIgnoreCase(name)).map(_.value)
  def getHeaders(name: String): Seq[String] =
    headers.filter(_.name.equalsIgnoreCase(name)).map(_.value)

  def addHeader(name: String, value: String): T =
    withHeaders(headers :+ StubParam(name, value))
  def removeHeader(name: String): T =
    withHeaders(headers.filterNot(_.name.equalsIgnoreCase(name)))
  def setHeader(name: String, value: String): T =
    removeHeader(name).addHeader(name, value)
}

trait StubMessage[T <: StubHeaders[T]] extends StubHeaders[T] {
  val body: Option[AnyRef]
}

case class StubRequest(
    val method: Option[String] = None, // optional so we can create filters
    val path: Option[String] = None,
    val params: List[StubParam] = Nil,
    val headers: List[StubParam] = Nil,
    val body: Option[AnyRef] = None) extends StubMessage[StubRequest] {

  def getParam(name: String): Option[String] =
    params.find(_.name.equalsIgnoreCase(name)).map(_.value)
  def getParams(name: String): Seq[String] =
    params.filter(_.name == name).map(_.value)

  // TODO: better way to do this?
  override def withHeaders(headers: List[StubParam]): StubRequest =
    copy(headers = headers)

  def nilLists() = copy( // for after Jackson deserialization (there _is_ a better way...)
    params = if (params != null) params else Nil,
    headers = if (headers != null) headers else Nil)
}

case class StubResponse(
    val status: Int,
    val headers: List[StubParam] = Nil,
    val body: Option[AnyRef] = None) extends StubMessage[StubResponse] {

  // TODO: better way to do this?
  override def withHeaders(headers: List[StubParam]): StubResponse =
    copy(headers = headers)

  def nilLists() = copy( // for after Jackson deserialization (there _is_ a better way...)
    headers = if (headers != null) headers else Nil)
}

case class StubExchange(
    val request: StubRequest,
    val response: StubResponse,
    val delay: Option[Int] = None,
    val script: Option[String] = None) {

  def nilLists() = copy( // for after Jackson deserialization (there _is_ a better way...)
    request = request.nilLists,
    response = response.nilLists)
}
