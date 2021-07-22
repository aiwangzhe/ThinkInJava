package com.wangzhe.springboot.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//�Զ����߼�������Ҫ��������
public class MyImportSelector implements ImportSelector {

	//����ֵ�����ǵ����뵽�����е����ȫ����
	//AnnotationMetadata:��ǰ��ע@Importע����������ע����Ϣ
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// TODO Auto-generated method stub
		//importingClassMetadata
		//������Ҫ����nullֵ
		return new String[]{"com.wangzhe.springboot.bean.Blue","com.wangzhe.springboot.bean.Yellow"};
	}

}
