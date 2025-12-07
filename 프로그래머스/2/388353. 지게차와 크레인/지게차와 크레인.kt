class Solution {
    fun solution(storage: Array<String>, requests: Array<String>): Int {
        val containerWidth = storage[0].length
        val containerHeight = storage.count()
        var remainboxSize = containerWidth*containerHeight
        var changedStorage = storage.copyOf()

        requests.forEach{
            if(remainboxSize == 0) return@forEach
            val tempStorage = changedStorage.copyOf()
            val useCrane = it.length > 1
            for(i in 0 until containerHeight){
                for(j in 0 until containerWidth){
                    if(tempStorage[i][j] == it[0]){
                        val isRemove = if(useCrane) true 
                        else canPickItem(tempStorage,i to j,null,containerWidth,containerHeight)
                        if(isRemove)  {
                            remainboxSize--
                            changedStorage[i]=changedStorage[i].replaceRange(j,j+1,"0")
                        }
                    }
                }
            }  
        }

        return remainboxSize
    }
    
    
    private fun canPickItem(
        item: Array<String>,
        startPos :Pair<Int,Int>,
        beforePos :Pair<Int,Int>?,
        width:Int,
        height:Int
    ) : Boolean{
        if(startPos.first-1 == -1 || startPos.first+1 == height 
           || startPos.second-1 == -1 || startPos.second+1 == width
          ) return true
        val visited = Array(height) { BooleanArray(width) }
        visited[startPos.first][startPos.second] = true
        val findPositions = mutableListOf(startPos)
        while(findPositions.isNotEmpty()){
            val findPos = findPositions.removeFirst()
            val x = findPos.first
            val y = findPos.second
            if(x-1 == -1 || x+1 == height || y-1 == -1 || y+1 == width) return true
            for(i in -1 .. 1  step 2){
                if(item[x+i][y] == '0' && !visited[x+i][y]){
                    visited[x+i][y] = true
                    findPositions.add(x+i to y)
                }
                if(item[x][y+i] == '0' && !visited[x][y+i]){
                    visited[x][y+i] = true
                    findPositions.add(x to y+i)
                } 
            }
        }
        
        return false
    }
}