import com.makingdevs.diaspora.GithubClient
import com.makingdevs.diaspora.Network

def client = GithubClient.instance
def network = Network.instance

user_1 = client.getUser("neodevelop")
followers_1 = client.getFollowersForUser(user_1)
user_1.followers = followers_1

user_2 = client.getUser("carlogilmar")
followers_2 = client.getFollowersForUser(user_2)
user_2.followers = followers_2

commonFollowers = network.commonFollowersFor(user_1, user_2)
println commonFollowers*.username
