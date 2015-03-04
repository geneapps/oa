package com.giro.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static boolean isNull(String s){
		
		if(s==null) return true;
		if(s.trim().equals("")) return true;
		return false;
		
	}
	
	
	@SuppressWarnings("unused")
	public static String delHTMLTag(String htmlStr){
		
		if(htmlStr==null) return "";
		
//        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
//        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 <([^>]*)>
         
//        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
//        Matcher m_script=p_script.matcher(htmlStr); 
//        htmlStr=m_script.replaceAll(""); //过滤script标签 
//         
//        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
//        Matcher m_style=p_style.matcher(htmlStr); 
//        htmlStr=m_style.replaceAll(""); //过滤style标签 
        htmlStr = htmlStr.replaceAll("<p .*?>", "\r\n"); 
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 

        return htmlStr.trim(); //返回文本字符串 
    }
	
	public static String toHtmlText(String text){
		
		if(text==null) return "";
		
		text = text.replaceAll("\r\n", "<br /><br />");
		text = text.replaceAll("\n\t", "");
		text = text.replaceAll("\n", "");
        return text.trim(); //返回文本字符串 
    }
	
	public static void main(String[] args){

		String html = "<P><SPAN style=\"FONT-FAMILY: 宋体; COLOR: black\"><SPAN>9.27</SPAN>行情延宕至今已有一个月，从技术面的角度说，<SPAN>10</SPAN>月以来，行情“在小区间震荡”日久，在不能凭借利好取得向上突破的情况下，下行调整的概率在提升，不能排除再次考验<SPAN>1999</SPAN>的可能。<SPAN></SPAN></SPAN> </P>\r\n<P style=\"TEXT-INDENT: 2em\"><SPAN style=\"FONT-FAMILY: 宋体; COLOR: black\">但必须强调，这种调整更多是“政策预期（寄望<SPAN>10</SPAN>月降准、降息）落空后”的短期下行、回调（最多算得上是“<SPAN>9.27</SPAN>”的终结），而在经济增速“触底（甚至反弹）”趋势没有逆转之前，<SPAN>A</SPAN>股中期趋势仍然保存着“向上修复”的能力，也即所谓的《<SPAN><A href=\"#contents_content_C354776_sharp\"><FONT color=#ff2222>9.27完结，但中期向上修复潜力延续</FONT></A></SPAN>》，不过，经济增速“缓慢筑底”过程当中的“曲折性”，从趋势上决定了此轮修复行情初期，股指表现的反复与波折。<SPAN></SPAN></SPAN> </P><p><p><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">政治因素在通道理论中起到什么作用？博览主任研究员徐志丹女士今日就《<A href=\"#contents_content_C354769_sharp\"><FONT color=#ff2222>通道理论再探：政治如何作用行情？》</FONT></A>进行探讨，她认为——在中国，政治高于经济，政治对经济的影响，往往通过政策、制度来决定资源的分配。在政府换届期，政治资源面临着重构与再分配，只有待政治资源重新组织之后，经济活动才会真正走上正轨。因此，政治资源配置经济目标。落实到经济目标上，政治资源的分配会影响到“保增长”、“调结构”、“控通胀”三者之间关系的重新定位。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">在行情通道中，政治资源的配置既会影响到下轨的经济基本面，也会影响到上轨的资金流动性。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">如果中国未来十年转型改革的步伐加快，政策紧缩会成为主流，短期会使得经济增速下行的速度加快，中国经济持续调整的时间会拉长，经济长期衰退决定了行情长期下降通道未有改变，同时上轨的流动性因政策紧缩亦不会有好的表现，导致行情的方向与空间都将不如人意。而长期来看，改革步伐加快有利于增强国人对中国经济发展的信心，虽然短期的阵痛不可避免，但长期发展有了希望令行情中长期趋势向好成为可能。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">如果中国未来十年转型改革的步伐趋缓，“保增长”思潮重新复辟，经济刺激方案可能会重新出台，短期会使得经济增速下行受到抑制，同时资金流动性会出现井喷，使得短期行情上涨有望。但长期来看，经济结构调整的进程受到进一步耽搁，中国经济积累的风险进一步加剧，给行情中长期走势蒙上了阴影。<SPAN></SPAN></SPAN> </P><p><p><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">股指期货推出已经<SPAN>2</SPAN>年半，回首这段时间的股市行情，股指期货的确给<SPAN>A</SPAN>股带来了深远的影响，虽然这一切都是潜移默化地在变迁。博览研究员感触最深的是，自从有了股指期货，股市的运行变得更加具有“变异性”或不确定性（尤其是短周期的波动），这大大增加了行情研判的难度。说得通俗点，这<SPAN>2</SPAN>年多来，股市行情越来越不好操作，虽然与熊市大背景缺乏持续性的系统投资机会有关，但即便是股市出现了阶段性的回暖，投资获利的难度也在显著提高，而这正是股指期货给市场带来的洗礼。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">期指究竟给市场带来了哪些冲击？我们又该如何善用期指风向标来指导投资决策或配置期指以优化投资策略？今日博览主任研究员揭冲先生对《<A href=\"#contents_content_C354777_sharp\"><FONT color=#ff2222>期指风向标再探讨</FONT></A>》——<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">在博览研究员看来，期指给市场带来的最大冲击还不是其价格发现功能，至少目前其对远期行情的价格领先功能还没有得到充分发挥，而是期指确确实实地改变了股市的波动节奏——不仅股市短线行情波动过程变得更加迂回（或者说是“纠结”），行情的突发性和欺骗性也在加强。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">而股指期货相对应股票现货具有两个显著的优势：一是<SPAN>T+0</SPAN>的交易制度，二是开盘前和开盘后各自多出<SPAN>15</SPAN>分钟的时间优势。这两个优势不仅能方便我们进行短周期方向的预判，而且可以用来实现快速再配置头寸，以应变突发行情。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">●股指期货的妙用之一就是——可以在极短的时间内买进，而且还可以在股市收盘后买进。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">●股指期货的妙用之二就是——股市开盘前的<SPAN>15</SPAN>分钟买进或卖出，降低踏空的成本或规避系统性风险。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">●股指期货的妙用之三就是——利用突发行情的激烈情绪做期现套利。<SPAN></SPAN></SPAN> </P><p><p><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">此外，《<A href=\"#contents_content_C354778_sharp\"><FONT color=#ff2222>期指独特的量价规律</FONT></A>》也能为我们研判行情提前释放出有价值的信号。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">1</SPAN><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">、总持仓量衡量整体价格趋势的“后劲”，增仓带动一波行情开始，减仓导致一波行情结束。势能的积累需要新增资金持续推动，一旦买力和卖力衰竭，就是行情变盘的时刻到来。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">2</SPAN><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">、主力合约前<SPAN>20</SPAN>名净持仓量突变与波段行情关联度较高，历史数据显示，当净持仓超过一定阈值后，分析净持仓的方向指示进行跟随操作、在净持仓从高位衰减的过程中反转头寸方向的策略有较大获利能力。<SPAN></SPAN></SPAN> </P>\r\n<p style='text-indent:2em'><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">3</SPAN><SPAN style=\"COLOR: black; FONT-FAMILY: 宋体\">、而成交量则是趋势推进力度的衡量器，适用传统的量价关系法则。<SPAN></SPAN></SPAN> </P><p>";


		System.out.println(html);
		
		try {
			System.out.println(java.net.URLEncoder.encode("中文","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(int i=0;i<100;i++){
//			System.out.println(i%45);
//		}
	}
	
	
}
