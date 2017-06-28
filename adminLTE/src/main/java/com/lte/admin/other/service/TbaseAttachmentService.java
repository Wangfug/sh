package com.lte.admin.other.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lte.admin.common.persistence.Page;
import com.lte.admin.common.service.BaseService;
import com.lte.admin.other.dao.TbaseAttachmentDao;
import com.lte.admin.other.entity.TbaseAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Andy
 */
@Service
@Transactional
public class TbaseAttachmentService extends BaseService<TbaseAttachment, Integer> {
	@Autowired
	private TbaseAttachmentDao tbaseAttachmentDao;

	public PageList<TbaseAttachment> getList(Page<TbaseAttachment> page, Map<String, Object> filters) {
		PageBounds pb = createPageBounds(page);
		return (PageList<TbaseAttachment>) tbaseAttachmentDao.getList(pb, filters);
	}

	public void save(TbaseAttachment tbaseAttachment) {
		tbaseAttachmentDao.save(tbaseAttachment);
	}

	public void update(TbaseAttachment tbaseAttachment) {
		tbaseAttachmentDao.update(tbaseAttachment);
	}

	public void deleteById(Long id) {
		tbaseAttachmentDao.deleteById(id);
	}

	public TbaseAttachment get(long id) {
		return tbaseAttachmentDao.get(id);
	}
}

