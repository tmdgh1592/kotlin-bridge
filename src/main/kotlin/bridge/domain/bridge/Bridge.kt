package bridge.domain.bridge

class Bridge(private val bridge: List<String>) {
    fun directionOf(index: Int) = bridge[index]
    fun size() = bridge.size
}