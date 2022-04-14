package com.codegym.demo.service.post;

import com.codegym.demo.dto.response.PostResponse;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.Post;
import com.codegym.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findAllByIdCompany(Long id) {
        return postRepository.findAllByIdCompany(id);
    }

    @Override
    public Iterable<Post> findAllByPositionContaining(String position) {
        return postRepository.findAllByPositionContaining(position);
    }

    @Override
    public Iterable<Post> findAllByTitleContaining(String title) {
        return postRepository.findAllByTitleContaining(title);
    }

    @Override
    public Iterable<Post> findAllByAddressContaining(String address) {
        return postRepository.findAllByAddressContaining(address);
    }

    @Override
    public Iterable<Post> findAllBySalaryContaining(double salary) {
        return postRepository.findAllBySalaryIsGreaterThan(salary);
    }

    @Override
    public Iterable<Post> searchAdvanced(String title, Integer salary, String exp, String address) {
        return postRepository.searchAdvanced(title, salary, exp, address);
    }

    @Override
    public PostResponse convert(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setAddress(post.getAddress());
        postResponse.setCompanyname(post.getCompany().getCompanyName());
        postResponse.setTittle(post.getTitle());
        postResponse.setExpireddate(post.getExpiredDate());
        postResponse.setSalary(post.getSalary());
        return postResponse;
    }

    @Override
    public List<Post> searchJob(String keyword, Long cat_id, Long city_id, double salary1, double salary2) {
        List<Post> postList = new ArrayList<>();
        if (keyword.equals("")) {
            postList.addAll(postRepository.findAll());
        } else {
            postList.addAll(postRepository.findAllByTitleContainsOrCompanyCompanyNameContainsOrAddressContains(keyword, keyword, keyword));
        }
        if (cat_id == -1) {
            postList.addAll(postRepository.findAll());
        } else {
            postList.addAll(postRepository.findAllByCategoryId(cat_id));
        }
        if (city_id == -1) {
            postList.addAll(postRepository.findAll());
        } else {
            postList.addAll(postRepository.findAllByCityId(city_id));
        }
        if (salary1 == -1) {
            postList.addAll(postRepository.findAll());
        } else {
            postList.addAll(postRepository.findAllBySalary(salary1, salary2));
        }
        Set<Post> postSet = this.getDuplicate(postList, 4);
        List<Post> posts = new ArrayList<>();
        for (Post post : postSet) {
            posts.add(post);
        }
        sortList(posts);
        return posts;
    }

    public Set<Post> getDuplicate(List<Post> postList, int number) {
//        Set<Post> posts = new HashSet<>();
//        Set<Post> postSet = postList.stream().filter(n -> !posts.add(n)).collect(Collectors.toSet());
        final Set<Post> postSet = new HashSet<>();

        for (Post post : postList) {
            int occurrences = Collections.frequency(postList, post);

            if (occurrences == number) {
                postSet.add(post);
            }
        }
        return postSet;
    }

    @Override
    public Page<PostResponse> findAllPage(Pageable pageable) {
        Page<Post> postList = postRepository.findPosts(true, pageable);
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : postList) {
            postResponses.add(this.convert(post));
        }
        return new PageImpl<>(postResponses, pageable, postResponses.size());
    }

    public List<Post> sortList(List<Post> posts) {
        posts.sort((left, right) -> (int) (left.getId() - right.getId()));
        return posts;
    }


    @Override
    public Iterable<Post> findTop5Company() {
        return postRepository.findTop5Company();
    }

    @Override
    public List<Post> findByCategory(Long cat_id, Long id) {
        return postRepository.findAllByCategory(cat_id, id);
    }

    @Override
    public List<Post> find6PostsOrderByIdDesc() {
        List<Post> posts = new ArrayList<>(6);
        List<Post> postList = postRepository.findAllByOrderByIdDesc();
        for (int i = 0; i < postList.size(); i++) {
            if (i < 6) {
                posts.add(postList.get(i));
            } else {
                break;
            }
        }
        return posts;
    }

    @Override
    public Iterable<Post> findPostsByRecommended() {
        return postRepository.findPostsByRecommendedIsTrue();
    }

    @Override
    public Iterable<Integer> findTop1Company() {
        return postRepository.findTop1Company();
    }

    @Override
    public Iterable<Post> findTop6PostRecommended() {
        return postRepository.findTop6PostRecommended();
    }

}
