package com.remote.come.controller;

import com.remote.come.common.Result;
import com.remote.come.common.ResultGenerator;
import com.remote.come.dao.JobDao;
import com.remote.come.entity.Job;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/info")
public class JobController {

    @Resource
    JobDao jobDao;

    // search for a job info
    @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<Job> getOne(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("缺少参数");
        }
        Job job = jobDao.getJobById(id);
        if (job == null) {
            return ResultGenerator.genFailResult("无此数据");
        }
        return ResultGenerator.genSuccessResult(job);
    }

    // search all jobs
    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Job>> queryAll() {
        List<Job> jobs = jobDao.findAllJobs();
        return ResultGenerator.genSuccessResult(jobs);
    }

    // insert one job
    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> insert(@RequestBody Job job) {
        // validate
        if (StringUtils.isEmpty(job.getTitle()) || StringUtils.isEmpty(job.getDescription())) {
            return ResultGenerator.genFailResult("缺少参数");
        }
        return ResultGenerator.genSuccessResult(jobDao.insertJob(job) > 0);
    }

    // update a job
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @ResponseBody
    public Result<Boolean> update(@RequestBody Job tempJob) {
        // validate
        if (tempJob.getId() == null || tempJob.getId() < 1 || StringUtils.isEmpty(tempJob.getTitle()) || StringUtils.isEmpty(tempJob.getDescription())) {
            return ResultGenerator.genFailResult("缺少参数");
        }

        Job job = jobDao.getJobById(tempJob.getId());
        if (job == null) {
            return ResultGenerator.genFailResult("参数异常");
        }
        job.setTitle(tempJob.getTitle());
        job.setDescription(tempJob.getDescription());
        return ResultGenerator.genSuccessResult(jobDao.updJob(job) > 0);
    }

    // delete a job
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("缺少参数");
        }
        return ResultGenerator.genSuccessResult(jobDao.delJob(id) > 0);
    }

}
