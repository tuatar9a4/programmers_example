class Solution {
    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        var remainTime : Long = limit - times.sum()
        if(remainTime == 0L) return diffs.maxOf {it}
        val levelToRetryTime = mutableMapOf<Int, Int>(0 to 0)
        for(i in 1 until diffs.size){
            levelToRetryTime[diffs[i]] = (levelToRetryTime[diffs[i]]?:0) + (times[i]+times[i-1])
        }
        val sortedKeys = levelToRetryTime.map { it.key }.sortedDescending()

        var standardLevel = 0
        var splitNum = 0L
        for(i in 1 until sortedKeys.size){
            splitNum +=  levelToRetryTime[sortedKeys[i-1]]!!
            val spendTime = splitNum.toLong() * (sortedKeys[i-1] - sortedKeys[i])
            remainTime -=spendTime
            if(remainTime <= 0){
                standardLevel = sortedKeys[i]
                break
            }
        }

        if(splitNum == 0L) return 1
        return Math.max((Math.ceil(standardLevel  - (remainTime / splitNum.toDouble()))),1.0).toInt()
    }
}