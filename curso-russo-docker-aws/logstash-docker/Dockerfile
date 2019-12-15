FROM docker.elastic.co/logstash/logstash:7.5.0
RUN rm -f /urs/share/logstash/pipeline/logstash.conf
COPY logstash.conf /urs/share/logstash/pipeline/logstash.conf
COPY logstash.yml config/logstash.yml
