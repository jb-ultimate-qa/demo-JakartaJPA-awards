package my.example.awards.queries;

import my.example.awards.model.dto.AwardDto;
import org.hibernate.annotations.processing.HQL;

import java.util.List;

public interface HqlAnnotated {

    @HQL("select new AwardDto(a.title) from Award a")
    List<AwardDto> getAwardInDto();

    @HQL("select new my.example.awards.model.dto.AwardDto(a.year, a.title) from Award a")
    List<AwardDto> getAwardInFqnDto();

    @HQL("select a from Award a")
    List<AwardDto> getAward();
}
