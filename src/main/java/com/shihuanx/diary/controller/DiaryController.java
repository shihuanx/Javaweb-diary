package com.shihuanx.diary.controller;

import com.shihuanx.diary.annotation.Log;
import com.shihuanx.diary.entity.Diary;
import com.shihuanx.diary.response.Result;
import com.shihuanx.diary.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/diary")
@Tag(name = "日记控制类",description = "提供日记CURD操作接口")
public class DiaryController {

    private static final Logger logger = Logger.getLogger(DiaryController.class.getName());

    @Autowired
    private DiaryService diaryService;

    @Operation(summary = "查询日记接口",description = "请输入每页展示的记录数pageSize(默认为5),页码pageNumber(默认为1)," +
           "日记内容content(支持模糊查询),标签内容tag(支持模糊查询),开始时间start,结束时间end,排序方式order(asc或desc)," +
            "排序根据sortBy(create_time或update_time)")
    @GetMapping
    public Result getDiary(@RequestParam(defaultValue ="5" ) Integer pageSize,
                           @RequestParam(defaultValue ="1" ) Integer pageNumber,
                           @RequestParam(required = false) String content,
                           @RequestParam(required = false) String tag,
                           @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               @RequestParam(defaultValue = "0000-01-01 00:00:00",required = false) LocalDateTime start,
                           @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               @RequestParam(defaultValue = "9999-12-31 23:59:59",required = false) LocalDateTime end,
                           @RequestParam(defaultValue = "desc") String order,
                           @RequestParam(defaultValue ="create_time" ) String sortBy) {
        logger.log(Level.INFO,"分页查询日记，参数：{0},{1},{2},{3},{4},{5},{6},{7}",
                new Object[]{pageSize,pageNumber,content,tag,start,end,order,sortBy});
        if(!(order.equals("desc") || order.equals("asc"))) {return Result.error("顺序只能为asc（升序）或desc（降序）");}
        if(!(sortBy.equals("create_time") || sortBy.equals("update_time"))) {return Result.error("只能通过创建时间或更新时间查询");}
        return Result.success(diaryService.getDiary(pageSize, pageNumber, content,tag, start, end, order, sortBy));
    }

    @Log
    @Operation(summary = "新增日记接口",description = "以json格式传入日记内容")
    @PostMapping
    public Result addDiary(@RequestBody Diary diary) {
        logger.log(Level.INFO,"添加日记：{0}", new Object[]{diary});
        diaryService.addDiary(diary);
        return Result.success();
    }

    @Log
    @Operation(summary = "删除日记接口",description = "在url中加入日记id，支持批量删除")
    @DeleteMapping("/{diaryIds}")
    public Result deleteDiary(@PathVariable List<Integer> diaryIds) {
        logger.log(Level.INFO,"删除id为{0}的日记",new Object[]{diaryIds});
        diaryService.deleteDiaryById(diaryIds);
        return Result.success();
    }

    @Log
    @Operation(summary = "修改日记接口",description = "以json格式传入日记内容和id")
    @PutMapping
    public Result updateDiary(@RequestBody Diary diary) {
        logger.log(Level.INFO,"更新日记：{0}", new Object[]{diary});
        diaryService.updateDiary(diary);
        return Result.success();
    }


}
