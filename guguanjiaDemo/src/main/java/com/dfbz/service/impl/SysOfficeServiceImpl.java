package com.dfbz.service.impl;

import com.dfbz.entity.SysOffice;
import com.dfbz.service.SysOfficeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Chen
 * @description 办公室
 * @date 2019/11/15 19
 */
@Service
@Transactional
public class SysOfficeServiceImpl extends IServiceImpl<SysOffice> implements SysOfficeService {
}
