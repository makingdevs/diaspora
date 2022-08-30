import com.makingdevs.diaspora.GithubClient
import com.makingdevs.diaspora.Network

def client = GithubClient.instance
def network = Network.instance

user_1 = client.getUser("neodevelop")
user_1 = client.putFollowersToUser(user_1)

user_2 = client.getUser("carlogilmar")
user_2 = client.putFollowersToUser(user_2)

commonFollowers = network.commonFollowersFor(user_1, user_2)

commonFollowers = commonFollowers.collect { user ->
  user = client.getUser(user.username)
  user = client.putFollowersToUser(user)
  user
}

grid = network.gridOfUsersAndFollowers(commonFollowers)

graphCommonFollowers = network.graphvizForFollowers(user_1, user_2, commonFollowers)
graph = network.graphvizForFollowersOfFollowers(grid)
//println graph
file = new File("graph.dot")
file.text = graphCommonFollowers + graph

println "Done!!!"
