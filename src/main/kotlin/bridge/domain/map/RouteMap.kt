package bridge.domain.map

import bridge.domain.bridge.Bridge
import bridge.domain.path.Path
import bridge.util.constant.DOWN
import bridge.util.constant.UP

class RouteMap(
    private val routeMap: List<ArrayList<Mark>> = listOf(arrayListOf(), arrayListOf()),
    private val bridge: Bridge
) {
    fun addPath(path: Path): RouteMap {
        increaseMapSize()
        updatePath(path, path.position())
        return RouteMap(routeMap, bridge)
    }

    private fun updatePath(path: Path, position: Int) {
        val bridgeDirection = bridge.directionOf(position)
        val playerDirection = path.userDirectionAsDigit(position)
        val isCorrectPath = path.checkDirection(bridgeDirection, position)

        if (isCorrectPath) {
            markCorrectPath(bridgeDirection, playerDirection, position)
        } else {
            markWrongPath(bridgeDirection, playerDirection, position)
        }
    }

    private fun markCorrectPath(bridgeDirection: String, userDirection: Int, round: Int) {
        when (bridgeDirection) {
            UP -> routeMap[userDirection][round] = Mark.CORRECT
            DOWN -> routeMap[userDirection][round] = Mark.CORRECT
        }
    }

    private fun markWrongPath(bridgeDirection: String, userDirection: Int, round: Int) {
        when (bridgeDirection) {
            UP -> routeMap[userDirection][round] = Mark.WRONG
            DOWN -> routeMap[userDirection][round] = Mark.WRONG
        }
    }

    private fun increaseMapSize() {
        routeMap[0].add(Mark.DEFAULT)
        routeMap[1].add(Mark.DEFAULT)
    }

    fun asList() = routeMap
}