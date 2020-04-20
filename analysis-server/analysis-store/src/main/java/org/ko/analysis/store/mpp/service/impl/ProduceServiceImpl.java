package org.ko.analysis.store.mpp.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.ko.analysis.store.mpp.domain.Produce;
import org.ko.analysis.store.mpp.repository.ProduceRepository;
import org.ko.analysis.store.mpp.service.ProduceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description: SalesServiceImpl <br>
 * date: 2020/4/13 22:15 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ProduceServiceImpl extends ServiceImpl<ProduceRepository, Produce> implements ProduceService {

}
