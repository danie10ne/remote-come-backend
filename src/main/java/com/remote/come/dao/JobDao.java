package com.remote.come.dao;

import com.remote.come.entity.Job;

import java.util.List;

public interface JobDao {
    /**
     * 返回数据列表
     *
     * @return
     */
    List<Job> findAllJobs();

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    Job getJobById(Integer id);

    /**
     * 添加
     *
     * @param Job
     * @return
     */
    int insertJob(Job Job);

    /**
     * 修改
     *
     * @param Job
     * @return
     */
    int updJob(Job Job);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delJob(Integer id);
}
