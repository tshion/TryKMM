package io.github.tshion.trykmmlib.webdto

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

/**
 * Repo Search Result Item
 *
 * @param id
 * @param node_id
 * @param name
 * @param full_name
 * @param owner
 * @param &#x60;private&#x60;
 * @param html_url
 * @param description
 * @param fork
 * @param url
 * @param created_at
 * @param updated_at
 * @param pushed_at
 * @param homepage
 * @param size
 * @param stargazers_count
 * @param watchers_count
 * @param language
 * @param forks_count
 * @param open_issues_count
 * @param master_branch
 * @param default_branch
 * @param score
 * @param forks_url
 * @param keys_url
 * @param collaborators_url
 * @param teams_url
 * @param hooks_url
 * @param issue_events_url
 * @param events_url
 * @param assignees_url
 * @param branches_url
 * @param tags_url
 * @param blobs_url
 * @param git_tags_url
 * @param git_refs_url
 * @param trees_url
 * @param statuses_url
 * @param languages_url
 * @param stargazers_url
 * @param contributors_url
 * @param subscribers_url
 * @param subscription_url
 * @param commits_url
 * @param git_commits_url
 * @param comments_url
 * @param issue_comment_url
 * @param contents_url
 * @param compare_url
 * @param merges_url
 * @param archive_url
 * @param downloads_url
 * @param issues_url
 * @param pulls_url
 * @param milestones_url
 * @param notifications_url
 * @param labels_url
 * @param releases_url
 * @param deployments_url
 * @param git_url
 * @param ssh_url
 * @param clone_url
 * @param svn_url
 * @param forks
 * @param open_issues
 * @param watchers
 * @param topics
 * @param mirror_url
 * @param has_issues
 * @param has_projects
 * @param has_pages
 * @param has_wiki
 * @param has_downloads
 * @param has_discussions
 * @param archived
 * @param disabled Returns whether or not this repository disabled.
 * @param visibility The repository visibility: public, private, or internal.
 * @param license
 * @param permissions
 * @param text_matches
 * @param temp_clone_token
 * @param allow_merge_commit
 * @param allow_squash_merge
 * @param allow_rebase_merge
 * @param allow_auto_merge
 * @param delete_branch_on_merge
 * @param allow_forking
 * @param is_template
 * @param web_commit_signoff_required
 */
@Serializable
internal data class RepoSearchResultItemDto(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val owner: NullableSimpleUserDto?,
    val `private`: Boolean,
    val html_url: String,
    val description: String?,
    val fork: Boolean,
    val url: String,
    val created_at: Instant,
    val updated_at: Instant,
    val pushed_at: Instant,
    val homepage: String?,
    val size: Int,
    val stargazers_count: Int,
    val watchers_count: Int,
    val language: String?,
    val forks_count: Int,
    val open_issues_count: Int,
    val master_branch: String? = null,
    val default_branch: String,
//    val score: Int,
    val forks_url: String,
    val keys_url: String,
    val collaborators_url: String,
    val teams_url: String,
    val hooks_url: String,
    val issue_events_url: String,
    val events_url: String,
    val assignees_url: String,
    val branches_url: String,
    val tags_url: String,
    val blobs_url: String,
    val git_tags_url: String,
    val git_refs_url: String,
    val trees_url: String,
    val statuses_url: String,
    val languages_url: String,
    val stargazers_url: String,
    val contributors_url: String,
    val subscribers_url: String,
    val subscription_url: String,
    val commits_url: String,
    val git_commits_url: String,
    val comments_url: String,
    val issue_comment_url: String,
    val contents_url: String,
    val compare_url: String,
    val merges_url: String,
    val archive_url: String,
    val downloads_url: String,
    val issues_url: String,
    val pulls_url: String,
    val milestones_url: String,
    val notifications_url: String,
    val labels_url: String,
    val releases_url: String,
    val deployments_url: String,
    val git_url: String,
    val ssh_url: String,
    val clone_url: String,
    val svn_url: String,
    val forks: Int,
    val open_issues: Int,
    val watchers: Int,
    val topics: List<String>? = null,
    val mirror_url: String?,
    val has_issues: Boolean,
    val has_projects: Boolean,
    val has_pages: Boolean,
    val has_wiki: Boolean,
    val has_downloads: Boolean,
    val has_discussions: Boolean? = null,
    val archived: Boolean,
    /* Returns whether or not this repository disabled. */
    val disabled: Boolean,
    /* The repository visibility: public, private, or internal. */
    val visibility: String? = null,
    val license: NullableLicenseSimpleDto?,
    val permissions: PermissionsDto? = null,
    val text_matches: List<SearchResultTextMatchesDto>? = null,
    val temp_clone_token: String? = null,
    val allow_merge_commit: Boolean? = null,
    val allow_squash_merge: Boolean? = null,
    val allow_rebase_merge: Boolean? = null,
    val allow_auto_merge: Boolean? = null,
    val delete_branch_on_merge: Boolean? = null,
    val allow_forking: Boolean? = null,
    val is_template: Boolean? = null,
    val web_commit_signoff_required: Boolean? = null,
)
