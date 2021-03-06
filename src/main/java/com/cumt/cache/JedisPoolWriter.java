package com.cumt.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/***
 * redis连接池配置
 * 
 * @author draymonder
 *
 */
public class JedisPoolWriter {
	/* redis连接池对象 */
	private JedisPool jedisPool;

	/***
	 * 传入配置 主机 端口号 重连时间  密码
	 * @param poolConfig
	 * @param host
	 * @param port
	 * @param timeout
	 * @param password
	 */
	public JedisPoolWriter(final JedisPoolConfig poolConfig, final String host, final int port,
			final int timeout, final String password) {
		try {
			jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 获取Redis连接池对象
	 * 
	 * @return
	 */
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	/***
	 * 注入Redis连接池对象
	 * 
	 * @param jedisPool
	 */
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

}
