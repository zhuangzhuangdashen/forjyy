package com.example.kafkaconsumer;

import kafka.admin.AdminClient;
import kafka.coordinator.group.GroupOverview;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/24.
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"app_log"}, groupId = "1")
    public void receive(String message) {
        System.out.println("app_log--消费消息:" + message);

        getAllGroupsForTopic("0", "0");
    }

    public static Set<String> getAllGroupsForTopic(String brokerListUrl, String topic) {
        AdminClient client = AdminClient.createSimplePlaintext(brokerListUrl);

        try {
            List<GroupOverview> allGroups = scala.collection.JavaConversions.seqAsJavaList(client.listAllGroupsFlattened().toSeq());
            Set<String> groups = new HashSet<>();
            for (GroupOverview overview : allGroups) {
                String groupID = overview.groupId();
                Map<TopicPartition, Object> offsets = scala.collection.JavaConversions.mapAsJavaMap(client.listGroupOffsets(groupID));
                Set<TopicPartition> partitions = offsets.keySet();
                for (TopicPartition tp : partitions) {
                    if (tp.topic().equals(topic)) {
                        groups.add(groupID);
                    }
                }
            }
            return groups;
        } finally {
            client.close();
        }
    }
}

