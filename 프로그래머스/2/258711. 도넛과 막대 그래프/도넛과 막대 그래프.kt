class Solution {
    fun solution(edges: Array<IntArray>): IntArray {
        var barCount = 0
        var donutsCount = 0
        var eightCount = 0
        
        //FanIn
        val fanInCount = edges.groupingBy{ it[1] }.eachCount()
        //FanOut
        val fanOutCount = edges.groupingBy{ it[0] }.eachCount()
        
        var createNode = 0
        fanOutCount.forEach{
             if(!fanInCount.keys.contains(it.key) && it.value >=2) {
                 createNode = it.key
                 return@forEach
             }
        }
        val startNodes = edges.filter{ it[0] == createNode}
        
        eightCount = fanOutCount.filter{it.value==2 && createNode !=it.key }.size

        fanInCount.keys.forEach{
            if(!fanOutCount.keys.contains(it)) barCount++
        }
        
        donutsCount = startNodes.size - eightCount - barCount

        return intArrayOf(createNode,donutsCount,barCount,eightCount)
    }
}