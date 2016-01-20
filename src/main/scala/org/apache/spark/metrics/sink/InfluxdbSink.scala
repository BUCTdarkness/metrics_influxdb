package org.apache.spark.metrics.sink

import java.util.Properties
import java.util.concurrent.TimeUnit

import com.codahale.metrics.MetricRegistry
import metrics_influxdb.{InfluxdbReporter, InfluxdbHttp}

import org.apache.spark.SecurityManager


/**
 * Created by shenjiyi on 2016/1/19.
 */
class InfluxdbSink(val property: Properties, val registry: MetricRegistry,
                   securityMgr: SecurityManager) extends Sink {


  val INFLUXDB_KEY_HOST = "host"
  val INFLUXDB_KEY_PORT = "port"
  val INFLUXDB_KEY_DB = "database"
  val INFLUXDB_KEY_USERNAME = "username"
  val INFLUXDB_KEY_PASSWORD = "password"

  def propertyToOption(prop: String): Option[String] = Option(property.getProperty(prop))

  if (!propertyToOption(INFLUXDB_KEY_HOST).isDefined) {
    throw new Exception("Influxdb sink requires 'host' property.")
  }

  if (!propertyToOption(INFLUXDB_KEY_PORT).isDefined) {
    throw new Exception("Influxdb sink requires 'port' property.")
  }

  if (!propertyToOption(INFLUXDB_KEY_DB).isDefined) {
    throw new Exception("Influxdb sink requires 'database' property.")
  }

  if (!propertyToOption(INFLUXDB_KEY_USERNAME).isDefined) {
    throw new Exception("Influxdb sink requires 'username' property.")
  }

  if (!propertyToOption(INFLUXDB_KEY_PASSWORD).isDefined) {
    throw new Exception("Influxdb sink requires 'password' property.")
  }

  val host = propertyToOption(INFLUXDB_KEY_HOST).get
  val port = propertyToOption(INFLUXDB_KEY_PORT).get
  val database = propertyToOption(INFLUXDB_KEY_DB).get
  val username = propertyToOption(INFLUXDB_KEY_USERNAME).get
  val password = propertyToOption(INFLUXDB_KEY_PASSWORD).get

  val influxdb = new InfluxdbHttp(host.toString, port.toInt, database.toString, username.toString, password.toString)

  val reporter: InfluxdbReporter = InfluxdbReporter.forRegistry(registry).build(influxdb)

  override def start(): Unit = {
    reporter.start(10, TimeUnit.SECONDS)
  }

  override def stop() {
    reporter.stop()
  }

  override def report() {
    reporter.report()
  }

}
