package com.yang

import java.sql.Timestamp

import org.apache.flink.streaming.api.scala._

object Demo {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val path: String = this.getClass.getResource("").getPath

    val startDS: DataStream[String] = env.readTextFile(path)
    val secondDS: DataStream[SensorReading] = startDS.map(date => {
      val strings: Array[String] = date.split(",")
      SensorReading(strings(0).toString, strings(1).toDouble, strings(2))
    })
    secondDS.keyBy(_.id)




  }
}
case class SensorReading(id:String,
                         temperature:Double,
                         date:String)
