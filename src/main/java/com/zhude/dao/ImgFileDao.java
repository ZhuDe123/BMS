package com.zhude.dao;

import com.zhude.entity.ImgFile;
import com.zhude.entity.ImgPage;
import org.bson.types.ObjectId;

import java.util.List;

public interface ImgFileDao {
    public List<ImgFile> selectAll(ImgPage imgPage);

    public ImgFile select(ObjectId id);

    public int insert(ImgFile imgFile);

    public int update(ImgFile imgFile);

    public int delete(ObjectId id);
}
