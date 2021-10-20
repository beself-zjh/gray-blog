/** 
 * projectName: gray-blog 
 * fileName: LabelServiceImpl.java 
 * packageName: com.blog.gray.service.impl 
 * date: Oct 16, 20213:27:53 PM 
 * copyright(c) 2017-2020 xxx公司
 */
package com.blog.gray.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.gray.dao.LabelRepository;
import com.blog.gray.domain.LabelDO;
import com.blog.gray.service.LabelService;
import com.blog.gray.service.RedisKeyConstant;
import com.blog.gray.util.RedisUtil;
import com.blog.gray.util.ViewCodeUtil;
import com.blog.gray.util.ViewCodeUtil.ViewResultCodeEnum;

/**
 * @title: LabelServiceImpl.java
 * @package com.blog.gray.service.impl
 * @description: 标签相关服务
 * @author: Zjh
 * @date: Oct 16, 2021 3:27:53 PM
 * @version: V1.0
 */
@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private LabelRepository labelRepository;

	/**
	 * @title: findById
	 * @description: 根据id查找标签信息
	 * @param id
	 * @return 标签实体类
	 */
	@Override
	public LabelDO findById(int id) throws RuntimeException {
		// cache读取
		if (redisUtil.hasKey(RedisKeyConstant.SINGLE_LABEL + id))
			return (LabelDO) redisUtil.get(RedisKeyConstant.SINGLE_LABEL + id);

		// 数据库读取
		Optional<LabelDO> labelOptional = labelRepository.findById(id);
		if (labelOptional.isPresent()) {
			LabelDO result = labelOptional.get();
			redisUtil.set(RedisKeyConstant.SINGLE_LABEL + id, result); // 放入缓存
			return result;
		} else { // 若数据库中不存在，抛出标签不存在异常
			throw ViewCodeUtil.toException(ViewResultCodeEnum.LABEL_NOT_EXIST);
		}
	}

	/**
	 * @title: findAll
	 * @description: 获取所有标签
	 * @return 全部标签
	 */
	@Override
	public List<LabelDO> findAll() {
		List<LabelDO> labelList = new ArrayList<LabelDO>();
		List<Integer> labelIdList = findAllId(); // 读取全部标签的id

		for (int id : labelIdList) { // 逐个读取标签属性
			try {
				labelList.add(findById(id));
			} catch (Exception e) { // 跳过不存在的标签，并打印警告，此时数据库存在不一致数据
				e.printStackTrace();
			}
		}

		return labelList;
	}

	/**
	 * @title: findAllId
	 * @description: 获取全部标签的id
	 * @return 所有标签id
	 */
	private List<Integer> findAllId() {
		if (redisUtil.hasKey(RedisKeyConstant.ALL_LABEL_ID)) // cache读取
			return Arrays.asList(redisUtil.lGet(RedisKeyConstant.ALL_LABEL_ID, 0, -1).toArray(new Integer[0]));

		// 数据库读取
		List<Integer> labelIdList = labelRepository.findAllId();
		redisUtil.lSet(RedisKeyConstant.ALL_LABEL_ID, labelIdList); // 放入缓存

		return labelIdList;
	}

}
