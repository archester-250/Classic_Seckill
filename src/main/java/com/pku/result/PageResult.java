package com.pku.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

//处理分页查询结果
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private long total;
    private List result;
}
