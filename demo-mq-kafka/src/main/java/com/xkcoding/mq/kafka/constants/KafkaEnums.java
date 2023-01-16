package com.xkcoding.mq.kafka.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Morgan
 * @create 2023-01-14-2:28
 */
@ToString
@AllArgsConstructor
public enum KafkaEnums {
    /**
     * 测试
     */
    TOPIC_A("topic-a", 1, "测试a"),
    TOPIC_B("topic-b", 2, "测试b"),
    TOPIC_C("topic-c", 3, "测试c");


    @Getter
    private String topic;
    @Getter
    private Integer partitionNum;
    @Getter
    private String desc;


    /** 检查是否为该枚举类中的constant 若不是则返回null */
    public static String isTopic(String topicName) {
        // 遍历枚举类
        for (KafkaEnums kafkaEnums : KafkaEnums.values()) {
            if (kafkaEnums.getTopic().equals(topicName)) {
                return kafkaEnums.getTopic();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String topicName = "topic-Morgan";
        String topic = isTopic(topicName);
        System.out.println(topic);
    }
}
