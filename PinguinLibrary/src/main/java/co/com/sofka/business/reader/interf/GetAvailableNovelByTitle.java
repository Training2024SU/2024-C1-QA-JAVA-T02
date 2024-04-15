package co.com.sofka.business.reader.interf;

import co.com.sofka.model.Novel;
import co.com.sofka.model.NovelLoan;

public interface GetAvailableNovelByTitle {
    public Novel getAvailableNovelByTitle(String title);
}
