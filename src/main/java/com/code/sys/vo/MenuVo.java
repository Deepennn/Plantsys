package com.code.sys.vo;

import com.code.sys.entity.Menu;
import lombok.Data;

/**

 */
@Data
public class MenuVo extends Menu {

    private Integer page;
    private Integer limit;

}
