/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apereo.portal.groups;

import java.util.Set;

import org.apereo.portal.EntityIdentifier;
import org.apereo.portal.IBasicEntity;

/**
 * An <code>IGroupMember</code> defines common behavior for both the leaf
 * <code>IEntity</code> and composite <code>IEntityGroup</code> sub-types
 * that together make up a Groups structure.
 * <p>
 * An <code>IGroupMember</code> can answer both its parents and its children but
 * has no api for adding or removing them.  These methods are defined on
 * the composite type, <code>IEntityGroup</code>, since you add a member to a
 * group, and not vice versa.
 * <p>
 * Because it extends <code>IBasicEntity</code>, an <code>IGroupMember</code> has
 * an <code>EntityIdentifier</code> that can be used to cache and lock it.  A leaf
 * <code>IGroupMember</code> also has a separate <code>EntityIdentifier</code> for
 * its underlying entity.  This second <code>EntityIdentifier</code> is used to
 * create and record group memberships.  In the case of a composite (non-leaf)
 * <code>IGroupMember</code>, both <code>EntityIdentifiers</code> are the same.
 * <p>
 * Take care to implement <code>equals()</code> and <code>hashCode()</code> so
 * that duplicates returned from "deep" methods can  be recognized.
 *
 * @author Dan Ellentuck
 */
public interface IGroupMember extends IBasicEntity {

/**
 * Returns an <code>Iterator</code> over the <code>Set</code> of this
 * <code>IGroupMember's</code> recursively-retrieved parent groups.
 *
 * @return java.util.Iterator
 */
Set<IEntityGroup> getAncestorGroups() throws GroupsException;

/**
 * Returns an <code>Iterator</code> over this <code>IGroupMember's</code> parent groups.
 * @return java.util.Iterator
 */
Set<IEntityGroup> getParentGroups() throws GroupsException;

/**
 * Returns the key of the underlying entity.
 * @return String
 */
String getKey();

    /**
     * Returns the underlying entity type.  For an <code>IEntityGroup</code>, this is
     * analogous to <code>Class</code> as applied to an <code>Array</code>; it is an
     * attribute of the group object.  For an <code>IEntity</code>, it is the entity
     * type of the group the entity belongs to, which may be any <code>Class</code>
     * the underlying entity can be legally cast to.  Thus, an <code>IEntity</code>
     * with an underlying entity of type <code>Manager</code> could have an entity
     * type of <code>Employee</code> as long as <code>Employee</code> was a
     * superclass of <code>Manager</code>.
     */
    Class<? extends IBasicEntity> getLeafType();

/**
 * Returns the type of the underlying entity.  For a group this will be
 * <code>IEntityGroup</code>.  For an entity, it will be the type of the
 * underlying <code>EntityIdentifier</code>.
 *
 * @deprecated Too confusing side-by-side with getLeafType
 */
@Deprecated
Class getType();
/**
 * Returns <code>EntityIdentifier</code> for this <code>IGroupMember's</code>
 * underlying entity.  In the case of an <code>IEntityGroup</code>, it will
 * be the <code>EntityIdentifier</code> for <code>this</code>.  In the case
 * of an  <code>IEntity</code>, it will be the <code>EntityIdentifier</code>
 * that identifies the underlying IPerson, ChannelDefinition, etc.
 *
 * @return org.apereo.portal.EntityIdentifier
 */
EntityIdentifier getUnderlyingEntityIdentifier();

/**
 * Answers if <code>this</code> is a recursive member of <code>IGroupMember</code> gm.
 * @return boolean
 * @param gm org.apereo.portal.groups.IGroupMember
 */
boolean isDeepMemberOf(IEntityGroup group) throws GroupsException;

/**
 * @return boolean
 */
boolean isGroup();
/**
 * Answers if <code>this</code> is a member of <code>IGroupMember</code> gm.
 * @return boolean
 * @param gm org.apereo.portal.groups.IGroupMember
 */
boolean isMemberOf(IEntityGroup group) throws GroupsException;

    /**
     * If this group member is an IEntityGroup, this method will return a
     * reference to it as such;  otherwise throws an exception.
     */
    IEntityGroup asGroup();

}
