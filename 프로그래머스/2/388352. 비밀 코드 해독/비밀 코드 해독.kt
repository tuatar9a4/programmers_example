class Solution {
    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        var answer: Int = 0
        var candidateList = mutableListOf<IntArray>()
        makeCandidate(candidateList,IntArray(5),1,0,n)
        q.forEachIndexed{ i , testNums ->
            candidateList = candidateList.filter{
                var sameCount = 0 
                var candidateIndex = 0 
                var testIndex = 0 
                while(
                    (testIndex < testNums.size) && 
                    (candidateIndex < it.size)
                ){
                    
                    when{
                        testNums[testIndex] < it[candidateIndex] -> {
                            testIndex++
                            continue
                        }
                        testNums[testIndex] == it[candidateIndex] -> {
                            sameCount++
                            testIndex++
                            candidateIndex++
                            continue
                        }
                        testNums[testIndex] > it[candidateIndex] -> {
                            candidateIndex++
                            continue
                        }
                    }
                }
                sameCount == ans[i]
            }.toMutableList()
        }
   
        return candidateList.size
    }
    
        private fun makeCandidate(arrayList: MutableList<IntArray>, numList : IntArray, start:Int, depth : Int, limit:Int){
        for(i in start..limit - (4-depth)){
            if(depth == 5){
                arrayList.add(numList.copyOf())
                break
            }
            numList[depth] = i
            makeCandidate(arrayList,numList,i+1,depth+1,limit)
        }
    }
    
}