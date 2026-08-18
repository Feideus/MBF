package com.mbf.audiosync.domain;

/**
 * Where a text's content originated from. The MVP is restricted to
 * public-domain / non-proprietary texts (see project scoping document,
 * section 6 "Technical and Legal Constraints") — copyrighted uploads are
 * modeled here for forward-compatibility but are not yet accepted.
 */
public enum SourceType {
    PUBLIC_DOMAIN,
    USER_UPLOAD,
    COMMUNITY_LIBRARY
}
