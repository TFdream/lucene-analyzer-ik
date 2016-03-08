package org.wltea.analyzer.ik_analyzer5;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
	@Test
	public void testIK(){
		
//		String text = "拿渡麻辣香锅味多美比肯德基好吃,日本料理比海鲜烤肉小吃";
		String text = "艾尚瑜伽悦体中心";
		
		Analyzer analyzer = new IKAnalyzer(true);
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("myfield", new StringReader(text));
			//获取词元位置属性
		    OffsetAttribute  offset = ts.addAttribute(OffsetAttribute.class); 
		    //获取词元文本属性
		    CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
		    //获取词元文本属性
		    TypeAttribute type = ts.addAttribute(TypeAttribute.class);
		    
		    //重置TokenStream（重置StringReader）
			ts.reset(); 
			//迭代获取分词结果
			while (ts.incrementToken()) {
			  System.out.println(offset.startOffset() + " - " + offset.endOffset() + " : " + term.toString() + " | " + type.type());
			}
			//关闭TokenStream（关闭StringReader）
			ts.end();   // Perform end-of-stream operations, e.g. set the final offset.

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//释放TokenStream的所有资源
			if(ts != null){
		      try {
				ts.close();
		      } catch (IOException e) {
				e.printStackTrace();
		      }
			}
			analyzer.close();
	    }
	}
}
