package cn.tarsknock.utilbox.service.impl;

import cn.tarsknock.utilbox.service.DnsService;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AliDnsService implements DnsService {

    @Value("${dns.ali.accessKeyId}")
    private String accessKeyId;
    @Value("${dns.ali.secret}")
    private String secret;
    @Value("${dns.ali.dnsRecordId}")
    private String recordId;

    @Override
    public Boolean registerDns(String ip, String domainName) {
        Config config = new Config().setAccessKeyId(accessKeyId).setAccessKeySecret(secret);
        config.endpoint="alidns.cn-beijing.aliyuncs.com";
        Client client = null;
        Boolean response = false;
        try {
            client = new Client(config);
            UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest()
                    .setRecordId(recordId).setRR(domainName).setType("A").setValue(ip).setLine("default");
            UpdateDomainRecordResponse updateDomainRecord = client.updateDomainRecord(updateDomainRecordRequest);
            if(Objects.equals(updateDomainRecord.getBody().recordId, recordId)){
                response = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public String getDnsList(){
        Config config = new Config().setAccessKeyId(accessKeyId).setAccessKeySecret(secret);
        config.endpoint="alidns.cn-beijing.aliyuncs.com";
        Client client = null;
        String response = "error";
        try {
            client = new Client(config);
            DescribeDomainRecordsRequest describeDomainRecordsRequest = new DescribeDomainRecordsRequest()
                    .setDomainName("tars-knock.cn");
            DescribeDomainRecordsResponse describeDomainRecordsResponse = client.describeDomainRecords(describeDomainRecordsRequest);
            describeDomainRecordsResponse.getBody().getDomainRecords().record.forEach(da -> {
                System.out.println(da.domainName+" "+da.RR+" "+da.recordId+" "+da.value);
            });
            response = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
