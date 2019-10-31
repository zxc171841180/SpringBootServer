package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 	��ǩ���Ҫ��
 * 		a.�̳�SimpleTagSupport�ࡣ
 *		b.override doTag����(ʵ�ִ����߼�)��
 *		c.��ǩ����Щ���ԣ���ǩ��Ҳ���ж�Ӧ�����ԣ�
 *		������Ҫ��ͬ������Ҫƥ�䣬�����ж�Ӧ��set������
 *	
 *	��ǩ��ִ�й���:
 *		step1.�������ݱ�ǩ�������ռ��ҵ���ǩ�������ļ�(.tld�ļ�),
 *	Ȼ�����ݱ�ǩ���ҵ���ǩ�࣬����������ǩ��ʵ������
 *      step2.��������ǩ������ֵ������ǩ����(ͨ�����ñ�ǩ
 *    �����set�����������ǩ������ֵ��Ҫ���㣬���������ȼ���)��
 *      step3.�������ñ�ǩ�����doTag������
 *  
 */
public class HelloTag extends SimpleTagSupport{
	
	private String msg;
	private int qty;
	
	public HelloTag(){
		System.out.println("HelloTag's constructor...");
	}
	
	public void setMsg(String msg) {
		System.out.println("HelloTag's setMsg...");
		this.msg = msg;
	}

	public void setQty(int qty) {
		System.out.println("HelloTag's setQty...");
		this.qty = qty;
	}

	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("HelloTag's dotag...");
		/*
		 * ͨ���̳���SimpleTagSupport��ķ�����
		 * ���pageContext���ö����ṩ�˻����������
		 * ��������ķ�����
		 */
		PageContext pctx = 
				(PageContext)getJspContext();
		JspWriter out = pctx.getOut();
		
		for(int i = 0; i < qty; i ++){
			out.println(msg + "<br/>");
		}
	}
	
	
	
	
	
	
	
}



