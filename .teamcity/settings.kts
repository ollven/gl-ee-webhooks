import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2025.11"

project {

    vcsRoot(Http101289311jetbrainsCommitStatusPublisherIssueRefsHeadsMaster)

    pipeline(CommitStatusPublisherIssue)
}

object Http101289311jetbrainsCommitStatusPublisherIssueRefsHeadsMaster : GitVcsRoot({
    name = "http://10.128.93.11/jetbrains/commit-status-publisher-issue#refs/heads/master"
    url = "http://10.128.93.11/jetbrains/commit-status-publisher-issue"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = token {
        userName = "oauth2"
        tokenId = "tc_token_id:CID_20a17a86f2dbfc52d34df9ca83559813:1:63addee6-6ba1-4b9f-8a42-1b0517371c4b"
    }
    param("pipelines.connectionId", "PROJECT_EXT_10")
})


object CommitStatusPublisherIssue : Pipeline({
    name = "Commit Status Publisher Issue"

    repositories {
        repository(Http101289311jetbrainsCommitStatusPublisherIssueRefsHeadsMaster)
    }

    triggers {
        vcs {
        }
    }

    job(CommitStatusPublisherIssue_Job1)
})

object CommitStatusPublisherIssue_Job1 : Job({
    id("Job1")
    name = "Job 1"

    steps {
        script {
            scriptContent = "ls"
        }
    }
})
