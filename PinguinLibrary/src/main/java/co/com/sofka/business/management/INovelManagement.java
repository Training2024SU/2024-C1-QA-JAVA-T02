package co.com.sofka.business.management;

import co.com.sofka.entities.Novel;

import java.util.List;

public interface INovelManagement {
    public void insertNovel(Novel novel);
    public List<Novel> getAllNovels();
    public Novel getNovelById(String id);
    public void updateNovel(Novel novel);
    public void deleteNovel(Novel novel);
}
