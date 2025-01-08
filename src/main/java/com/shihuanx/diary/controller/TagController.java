package com.shihuanx.diary.controller;

import com.shihuanx.diary.annotation.Log;
import com.shihuanx.diary.entity.Tag;
import com.shihuanx.diary.request.AddTagRequest;
import com.shihuanx.diary.response.Result;
import com.shihuanx.diary.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tag")
@io.swagger.v3.oas.annotations.tags.Tag(name = "标签控制类",description = "提供标签接口")
public class TagController {

    private static final Logger logger = Logger.getLogger(TagController.class.getName());

    @Autowired
    private TagService tagService;

    //添加标签
    @Log
    @PostMapping
    @Operation(summary = "添加标签接口",description = "以json格式传入日记id和标签集合，标签集合中有多个标签内容，支持批量添加标签")
    public Result addTag(@RequestBody AddTagRequest addTagRequest) {
        logger.log(Level.INFO,"向日记{0}添加标签：{1}",new Object[]{addTagRequest.getDiaryId(),addTagRequest.getTags()});
        tagService.addTagsByDiaryId(addTagRequest.getTags(),addTagRequest.getDiaryId());
        //return tagService.ifDiaryExist(addTagRequest.getDiaryId());
        return Result.success();
    }

    //通过标签ID删除标签
    @Log
    @DeleteMapping("/{tagIds}")
    @Operation(summary = "删除标签接口",description = "在url中加入标签id，支持批量删除，并传入日记id")
    public Result deleteTagsByTagIds(@PathVariable List<Integer>tagIds,@RequestParam Integer diaryId) {
        logger.log(Level.INFO,"删除{0}日记的标签：{1}",new Object[]{diaryId,tagIds});
        tagService.deleteTagsByTagIds(tagIds,diaryId);
        return Result.success();
    }

    //删除一个标签下的所有日记
    @Log
    @DeleteMapping("/all/{tagIds}")
    @Operation(summary = "删除一个标签下的所有日记接口",description = "在url中传入标签id，可传入多个标签")
    public Result deleteDiariesByTags(@PathVariable List<Integer> tagIds) {
        logger.log(Level.INFO,"删除{0}标签下的所有日记",new Object[]{tagIds});
        tagService.deleteDiariesByTags(tagIds);
        return Result.success();
    }

    //修改标签
    @Log
    @PutMapping
    @Operation(summary = "修改标签接口",description = "以json格式传入标签内容和标签id")
    public Result updateTag(@RequestBody Tag tag){
        logger.log(Level.INFO,"修改标签：{0}",new Object[]{tag});
        tagService.updateTag(tag);
        return Result.success();
    }


}
