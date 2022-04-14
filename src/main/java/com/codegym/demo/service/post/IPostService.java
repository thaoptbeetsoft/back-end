package com.codegym.demo.service.post;

import com.codegym.demo.dto.response.PostResponse;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.Post;
import com.codegym.demo.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByIdCompany(Long id);

    Iterable<Post> findAllByPositionContaining(String position);

    Iterable<Post> findAllByTitleContaining(String title);

    Iterable<Post> findAllByAddressContaining(String address);

    Iterable<Post> findAllBySalaryContaining(double salary);

    Iterable<Post> searchAdvanced(String title, Integer salary, String exp, String address);

    PostResponse convert(Post post);

    List<Post> searchJob(String keyword, Long cat_id, Long city_id, double salary1, double salary2);

    Page<PostResponse> findAllPage(Pageable pageable);

    Iterable<Post> findTop5Company();

    List<Post> findByCategory(Long cat_id, Long id);

    List<Post> find6PostsOrderByIdDesc();

    Iterable<Post> findPostsByRecommended();

    Iterable<Integer> findTop1Company();

    Iterable<Post> findTop6PostRecommended();


}
