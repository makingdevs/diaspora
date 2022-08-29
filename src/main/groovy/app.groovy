import com.makingdevs.diaspora.GithubClient
import com.makingdevs.diaspora.Network

def client = GithubClient.instance
def network = Network.instance

user_1 = client.getUser("neodevelop")
followers_1 = client.getFollowersForUser(user_1)
userWithFollowers_1 = [:]
userWithFollowers_1."${user_1.login}" = followers_1.flatten()*.login
println userWithFollowers_1
println "-"*100

user_2 = client.getUser("carlogilmar")
followers_2 = client.getFollowersForUser(user_2)
userWithFollowers_2 = [:]
userWithFollowers_2."${user_2.login}" = followers_2.flatten()*.login
println userWithFollowers_2
println "-"*100

commonFollowers = network.commonFollowers(userWithFollowers_1.first(), userWithFollowers_2.first())
println commonFollowers
