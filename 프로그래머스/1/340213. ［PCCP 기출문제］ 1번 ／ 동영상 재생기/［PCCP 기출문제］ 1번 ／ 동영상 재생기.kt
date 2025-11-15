class Solution {
    fun solution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {

        val lenToSec = translateToSec(video_len)
        val opStart = translateToSec(op_start)
        val opEnd = translateToSec(op_end)
        var posToSec = checkJumpOp(translateToSec(pos),opStart,opEnd)
        commands.forEach{
            when(it){
                "next"->{
                    posToSec = nextTime(posToSec,lenToSec)
                }
                "prev"->{
                    posToSec = prevTime(posToSec)
                }
            }
            posToSec = checkJumpOp(posToSec,opStart,opEnd)
        }
        return tranlateToTime(posToSec)
    }
    
    private fun translateToSec(time:String):Int{
        val seperateTime = time.split(":")
        val min = (seperateTime.getOrNull(0)?:"0").toInt() * 60
        val sec = (seperateTime.getOrNull(1)?:"0").toInt()
        return min+sec
    }
    
    private fun tranlateToTime(seconds :Int):String{
        val min = (seconds / 60).toString().padStart(2,'0')
        val sec = (seconds % 60).toString().padStart(2,'0')
        return "$min:$sec"
    }
    
    private fun prevTime(seconds:Int):Int{
        return Math.max(seconds-10,0)
    }
    
    private fun nextTime(seconds:Int,length:Int):Int{
        val afterNextSec = seconds+10
        return if(length < afterNextSec) length
        else afterNextSec
    }
    
    private fun checkJumpOp(seconds:Int,opStartSec:Int,opEndSec:Int):Int{
        return if(opStartSec <= seconds && seconds <= opEndSec) opEndSec  
        else seconds
    }
}