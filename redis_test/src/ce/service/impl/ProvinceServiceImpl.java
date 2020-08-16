package ce.service.impl;

import ce.dao.ProvinceDao;
import ce.dao.impl.ProvinceDaoImpl;
import ce.domain.Province;
import ce.jedis.util.JedisPoolUtils;
import ce.service.ProvinceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao dao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /**
     * 使用缓存
     *
     * @return
     */
    @Override
    public String findAllJson() {
        //1.先从redis中查询数据
        //1.获取redis 的哭护短链接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");

        //2.判断province_json数据是否为null
        if (province_json == null || province_json.length() == 0) {
            //reddis没有数据
            //2.1从数据库中查询
            List<Province> ps = dao.findAll();
            //2.2将list序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(ps);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //2.3将json数据存入redis
            jedis.set("province", province_json);
            jedis.close();
        }
        return province_json;
    }
}
