import com.makingdevs.diaspora.GithubClient
import com.makingdevs.diaspora.Network

def client = GithubClient.instance
def network = Network.instance

user_1 = client.getUser("neodevelop")
user_1 = client.putFollowersToUser(user_1)

user_2 = client.getUser("carlogilmar")
user_2 = client.putFollowersToUser(user_2)

commonFollowers = network.commonFollowersFor(user_1, user_2)
println commonFollowers*.username
