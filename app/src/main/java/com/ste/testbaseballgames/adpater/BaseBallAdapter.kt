package com.ste.testbaseballgames.adpater

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ste.testbaseballgames.R
import com.ste.testbaseballgames.data.BaseBallData

class BaseBallAdapter(context: Context, res:Int, list:ArrayList<BaseBallData>) :ArrayAdapter<BaseBallData>(context,res,list){

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null){
            tempRow = inf.inflate(R.layout.baseball_list_item,null)
        }

        var row = tempRow!!
        var inputList = mList.get(position)
        var inputTxt = row.findViewById<TextView>(R.id.baseballTxt)
        inputTxt.text = inputList.message

        if(inputList.fromWho == "CPU"){
            inputTxt.gravity = Gravity.LEFT
        }else{
            inputTxt.gravity = Gravity.RIGHT
        }



        return row
    }
}