package com.ste.testbaseballgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ste.testbaseballgames.adpater.BaseBallAdapter
import com.ste.testbaseballgames.data.BaseBallData
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : BaseActivity() {

    var questionList = ArrayList<Int>()
    var answerList = ArrayList<Int>()

    var baseBallList = ArrayList<BaseBallData>()
    var baseBallAdapter:BaseBallAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setQuestion()
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        mainBtn.setOnClickListener {
            var inputTxt = mainEdtTxt.text.toString()

            answerList.clear()
            for(i in 0..2){
                answerList.add(inputTxt.substring(i,i+1).toInt())
            }

            baseBallList.add(BaseBallData("${inputTxt}","ME"))
            baseBallAdapter = BaseBallAdapter(this,R.layout.baseball_list_item,baseBallList)
            mainListView.adapter = baseBallAdapter
            baseBallAdapter?.notifyDataSetChanged()
            mainListView.smoothScrollToPosition(baseBallList.size-1)

            checkAnswer()
        }
    }

    override fun setValues() {

    }

    fun setQuestion(){
        while(true) {
            var qNum = Random.nextInt(1, 10)
            if(!questionList.contains(qNum)){
                questionList.add(qNum)
                Log.d("로그","${qNum}")
            }

            if(questionList.size == 3){
                break
            }
        }
    }

    fun checkAnswer(){
        var strike = 0
        var ball = 0
        for(i in 0..2){
            for(j in 0..2){
                if(questionList.get(i) == answerList.get(j)){
                    if(i == j){
                        strike++
                    }else{
                        ball++
                    }
                }
            }
        }

        baseBallList.add(BaseBallData("${strike}S ${ball}B","CPU"))
        baseBallAdapter?.notifyDataSetChanged()
        mainListView.smoothScrollToPosition(baseBallList.size-1)
    }
}
