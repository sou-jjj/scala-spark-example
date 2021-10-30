.PHONY: package local_spark_submit

JDWP=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005
SPARK_BIN=/spark/bin
SPARK_MASTER=local[*]
TARGET=./target/scala-2.12

clean:
	sbt clean

assembly:
	sbt assembly

watch:
	sbt '~assembly'

scalafmtCheck:
	sbt scalafmtCheck test:scalafmtCheck scalafmtSbtCheck

spark_shell:
	$(SPARK_BIN)/spark-shell

spark_submit/WordCountJob:
	$(SPARK_BIN)/spark-submit \
	--master $(SPARK_MASTER) \
	--class com.soujjj.scalaspark.example.WordCountJob \
	--conf spark.driver.extraJavaOptions=$(JDWP) \
	$(TARGET)/scala-spark-example.jar

spark_submit/BreadBasketJob:
	$(SPARK_BIN)/spark-submit \
	--master $(SPARK_MASTER) \
	--class com.soujjj.scalaspark.example.sql.BreadBasketJob \
	--conf spark.driver.extraJavaOptions=$(JDWP) \
	$(TARGET)/scala-spark-example.jar

spark_submit/MarketingCampaignJob:
	$(SPARK_BIN)/spark-submit \
	--master $(SPARK_MASTER) \
	--class com.soujjj.scalaspark.example.sql.MarketingCampaignJob \
	--conf spark.driver.extraJavaOptions=$(JDWP) \
	$(TARGET)/scala-spark-example.jar

