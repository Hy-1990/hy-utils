package com.bigdata.hy_tools.ansj;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.library.StopLibrary;
import org.ansj.library.SynonymsLibrary;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.recognition.impl.SynonymsRecgnition;
import org.ansj.splitWord.Analysis;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.util.MyStaticValue;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.SmartForest;

import java.util.ArrayList;
import java.util.List;

/** @Author huyi @Date 2020/12/8 9:22 @Description: */
public class AnsjTest {
  public static void main(String[] args) {

    Analysis analysis = new DicAnalysis();
    // 是否开启人名识别
    analysis.setIsNameRecognition(true);
    // 是否开启数字识别
    analysis.setIsNumRecognition(true);
    // 是否开启量词识别
    analysis.setIsQuantifierRecognition(true);
    // 是否保留原字符
    analysis.setIsRealName(true);

    String sentence = "今天南京没有雨，并且哈哈没有来到中国南京欢乐谷,好欢乐。";

    String hyDic = "hy-dic";
    DicLibrary.put(hyDic, "", new Forest());
    MyStaticValue.ENV.put(hyDic, "");
    DicLibrary.insertOrCreate(
        hyDic, "中国南京欢乐谷", DicLibrary.DEFAULT_NATURE, Integer.parseInt(DicLibrary.DEFAULT_FREQ_STR));
    analysis.setForests(DicLibrary.get(hyDic));
    String hyStop = "hy-stop";
    StopLibrary.put(hyStop, "", new StopRecognition());
    MyStaticValue.ENV.put(hyStop, "");
    StopLibrary.insertStopWords(hyStop, "南京", "哈哈");
    String hySyn = "hy-syn";
    SynonymsLibrary.put(hySyn, "", new SmartForest<>());
    MyStaticValue.ENV.put(hySyn, "");
    SynonymsLibrary.append(hySyn, new String[] {"欢乐", "高兴", "开心"});

    Result result = analysis.parseStr(sentence);
    System.out.println("1--->  " + result);
    result.recognition(StopLibrary.get(hyStop));
    System.out.println("2--->  " + result);
    result.recognition(new SynonymsRecgnition(SynonymsLibrary.get(hySyn)));
    System.out.println("3--->  " + result);
    List<AnalyzerResult> list = new ArrayList<>(result.size());
    for (Term term : result.getTerms()) {
      AnalyzerResult analyzerResult = new AnalyzerResult();
      analyzerResult.setName(term.getName());
      analyzerResult.setNature(term.getNatureStr());
      analyzerResult.setOffe(term.getOffe());
      analyzerResult.setRealName(term.getRealName());
      analyzerResult.setSynonyms(null);
      if (null != term.getSynonyms()) {
        analyzerResult.setSynonyms(
            term.getSynonyms().toArray(new String[term.getSynonyms().size()]));
      }
      list.add(analyzerResult);
    }
    list.forEach(System.out::println);
  }
}
