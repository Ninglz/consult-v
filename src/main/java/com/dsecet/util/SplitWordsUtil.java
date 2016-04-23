package com.dsecet.util;

import com.google.common.collect.Lists;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;
import org.ansj.util.MyStaticValue;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by penghongqin on 14-10-20.
 */
public class SplitWordsUtil{

    static{
        URL path = SplitWordsUtil.class.getResource("/");
        MyStaticValue.userLibrary=path.getPath()+"dictionary/userLibrary.dic";
        MyStaticValue.ambiguityLibrary=path.getPath()+"dictionary/ambiguity.dic";
        FilterModifWord.insertStopWord(" ");
        FilterModifWord.insertStopWord("―");
        FilterModifWord.insertStopNatures("w");
    }

    public static final Set<String> splitWords(String sentence){
        Set<String> keywords = new HashSet<String>();
        List<Term> terms = ToAnalysis.parse(sentence);
        terms = FilterModifWord.modifResult(terms);
        terms.forEach(s -> keywords.add(s.getName()));
        return keywords;
    }

    public static void main(String[] args){
        /*Set<String> keywords = SplitWordsUtil.splitWords("smart马自达3星骋三厢2011款――1.6L 手动 舒适型 马自达3星骋 国4");
        keywords.forEach(s -> System.out.println(s));*/
/*        List<Term> parse = BaseAnalysis.parse("让战士们过一个欢乐祥和的新春佳节。");

        parse.forEach(t ->  System.out.println(t.getName()));*/

        //增加新词,中间按照'\t'隔开
        /*UserDefineLibrary.insertWord("我不喜欢甲醛", "userDefine", 1000) ;
        List terms = ToAnalysis.parse("我不喜欢范德萨发顺丰甲醛") ;
        new NatureRecognition(terms).recognition();
        System.out.println("增加新词例子:"+terms);*/
        //删除词语,只能删除.用户自定义的词典.
       /* UserDefineLibrary.removeWord("我不喜欢甲醛") ;
        terms = ToAnalysis.parse("我不喜欢范德萨发顺丰甲醛") ;
        new NatureRecognition(terms).recognition() ;
        System.out.println("删除用户自定义词典例子:"+terms);*/
/*        UserDefineLibrary.insertWord("甲醛过", "userDefine", 1000);
        List terms = ToAnalysis.parse("我不喜欢甲醛过") ;
        new NatureRecognition(terms).recognition();*/
        Set<String> keywords = SplitWordsUtil.splitWords("这个东西甲醛过量了");
        String querySql = "select * from news where";
        List<String> likeList = Lists.newArrayList();
        keywords.forEach(k -> {
            likeList.add(" t.name like '" + k + "%'");
        });
        querySql += StringUtils.join(likeList, " or") + " and n.status = 1 group by n.main_title order by n.created";
        System.out.println(querySql);
    }
}
