class Solution {
    fun solution(n: Long, bans: Array<String>): String {
        var findIndex = n
        var overNumberList = mutableListOf<Long>()
        bans.map{
            it.translateStrToIndex()
        }.sorted().forEach{
            if(it <= findIndex) findIndex++
            else overNumberList.add(it)
        }
        overNumberList.forEach{
            if(it <= findIndex) findIndex++
        }
        return findIndex.translateIndexToStr()
       
    }
    
    private fun String.translateStrToIndex():Long{
        val strList = this.toList()
        var index = 0L 
        strList.reversed().forEachIndexed{ i , v ->
            val number = v.toLong() - 96
            // 왜 pow 안되냐고...
            // val indexNumber = 26.toDouble().pow(i)
            val indexNumber = 26L.powNumber(i)
            index += (indexNumber * number)
        }
        return index
    }
    
    private fun Long.translateIndexToStr():String{
        var string =""
        var checkNum = this
        while(checkNum!=0L){
            var dividerNum = checkNum % 26L
            if(dividerNum == 0L ) {
                dividerNum = 26
                checkNum -= 26
            }
            val str = (dividerNum + 96).toChar()
            string = str + string
            checkNum = checkNum/26L
        }
        return string
    }
    
    private fun Long.powNumber(x : Int):Long{
        var originNum = 1L
        repeat(x){ originNum = originNum * this }
        return originNum
    }
}