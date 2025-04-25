package com.ca.test10.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Data // Setter Getter Constructor 다 만들어짐
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 제외한
      // 기본 생성자만 생성
public class StudentDto {
    // @Setter
    // @Getter // 세밀하게 하려면 Setter Getter 다 가능
    private String name;

    // @Setter // 이런식으로 붙여서 사용가능
    private int age;
    private int score;

    /*
     * 
     * << lombok >>
     * 자바쪽에서 필수로 가져오는 라이브러리
     * setter getter 만들어줌
     * 
     */
}
