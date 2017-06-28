package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.other.dao.CompAssociatedDao;
import com.lte.admin.other.entity.CompAssociated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class CompAssociatedService extends BaseService<CompAssociated, Integer>{
	@Autowired
	private CompAssociatedDao compAssociatedDao;


    public List<CompAssociated> getAllList() {
        return compAssociatedDao.getCompAssociatedList();
    }
    public PageList<CompAssociated> getList(Page<CompAssociated> page, Map<String, Object> filters) {
        PageBounds pb = createPageBounds(page);
        return (PageList<CompAssociated>) compAssociatedDao.getList(pb, filters);
    }

    public void save(CompAssociated compAssociated) {
        compAssociatedDao.save(compAssociated);
    }

    public void update(CompAssociated compAssociated) {
        compAssociatedDao.update(compAssociated);
    }

    public void deleteById(Long id) {
        compAssociatedDao.deleteById(id);
    }

    public CompAssociated get(long id) {
        return compAssociatedDao.get(id);
    }
}

