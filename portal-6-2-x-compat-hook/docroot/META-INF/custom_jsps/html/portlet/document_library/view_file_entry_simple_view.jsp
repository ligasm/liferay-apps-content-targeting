<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
FileEntry fileEntry = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

FileVersion fileVersion = fileEntry.getFileVersion();

boolean hasAudio = AudioProcessorUtil.hasAudio(fileVersion);
boolean hasImages = ImageProcessorUtil.hasImages(fileVersion);
boolean hasPDFImages = PDFProcessorUtil.hasImages(fileVersion);
boolean hasVideo = VideoProcessorUtil.hasVideo(fileVersion);

boolean showImageContainer = false;
%>

<div class="view">
	<div class="body-row">
		<aui:model-context bean="<%= fileVersion %>" model="<%= DLFileVersion.class %>" />

		<div>

			<%
			int previewFileCount = 0;
			String previewFileURL = null;
			String[] previewFileURLs = null;
			String videoThumbnailURL = null;

			String previewQueryString = null;

			if (hasAudio) {
				previewQueryString = "&audioPreview=1";
			}
			else if (hasImages) {
				previewQueryString = "&imagePreview=1";
			}
			else if (hasPDFImages) {
				previewFileCount = PDFProcessorUtil.getPreviewFileCount(fileVersion);

				previewQueryString = "&previewFileIndex=";

				previewFileURL = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString);
			}
			else if (hasVideo) {
				previewQueryString = "&videoPreview=1";

				videoThumbnailURL = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, "&videoThumbnail=1");
			}

			if (Validator.isNotNull(previewQueryString)) {
				if (hasAudio) {
					previewFileURLs = new String[PropsValues.DL_FILE_ENTRY_PREVIEW_AUDIO_CONTAINERS.length];

					for (int i = 0; i < PropsValues.DL_FILE_ENTRY_PREVIEW_AUDIO_CONTAINERS.length; i++) {
						previewFileURLs[i] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString + "&type=" + PropsValues.DL_FILE_ENTRY_PREVIEW_AUDIO_CONTAINERS[i]);
					}
				}
				else if (hasVideo) {
					if (PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS.length > 0) {
						previewFileURLs = new String[PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS.length];

						for (int i = 0; i < PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS.length; i++) {
							previewFileURLs[i] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString + "&type=" + PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS[i]);
						}
					}
					else {
						previewFileURLs = new String[1];

						previewFileURLs[0] = videoThumbnailURL;
					}
				}
				else {
					previewFileURLs = new String[1];

					previewFileURLs[0] = DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, previewQueryString);
				}

				previewFileURL = previewFileURLs[0];

				if (!hasPDFImages) {
					previewFileCount = 1;
				}
			}

			request.setAttribute("view_file_entry.jsp-supportedAudio", String.valueOf(hasAudio));
			request.setAttribute("view_file_entry.jsp-supportedVideo", String.valueOf(hasVideo));

			request.setAttribute("view_file_entry.jsp-previewFileURLs", previewFileURLs);
			request.setAttribute("view_file_entry.jsp-videoThumbnailURL", videoThumbnailURL);
			%>

			<c:choose>
				<c:when test="<%= previewFileCount == 0 %>">
					<c:choose>
						<c:when test="<%= !DLProcessorRegistryUtil.isPreviewableSize(fileVersion) && (AudioProcessorUtil.isAudioSupported(fileVersion.getMimeType()) || ImageProcessorUtil.isImageSupported(fileVersion.getMimeType()) || PDFProcessorUtil.isDocumentSupported(fileVersion.getMimeType()) || VideoProcessorUtil.isVideoSupported(fileVersion.getMimeType())) %>">
							<div class="alert alert-info">
								<liferay-ui:message key="file-is-too-large-for-preview-or-thumbnail-generation" />
							</div>
						</c:when>
						<c:when test="<%= AudioProcessorUtil.isAudioSupported(fileVersion) || ImageProcessorUtil.isImageSupported(fileVersion) || PDFProcessorUtil.isDocumentSupported(fileVersion) || VideoProcessorUtil.isVideoSupported(fileVersion) %>">
							<div class="alert alert-info">
								<liferay-ui:message key="generating-preview-will-take-a-few-minutes" />
							</div>
						</c:when>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="<%= hasAudio %>">
							<div class="lfr-preview-audio" id="<portlet:namespace />previewFile">
								<div class="lfr-preview-audio-content" id="<portlet:namespace />previewFileContent"></div>
							</div>

							<liferay-util:include page="/html/portlet/document_library/player.jsp" />
						</c:when>
						<c:when test="<%= hasImages %>">
							<c:choose>
								<c:when test="<%= showImageContainer %>">
									<div class="lfr-preview-file lfr-preview-image" id="<portlet:namespace />previewFile">
										<div class="lfr-preview-file-content lfr-preview-image-content" id="<portlet:namespace />previewFileContent">
											<div class="lfr-preview-file-image-current-column">
												<div class="lfr-preview-file-image-container">
													<img class="lfr-preview-file-image-current" src="<%= previewFileURL %>" />
												</div>
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<img class="lfr-preview-file-image-current" src="<%= previewFileURL %>" />
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="<%= hasVideo %>">
							<div class="lfr-preview-file lfr-preview-video" id="<portlet:namespace />previewFile">
								<div class="lfr-preview-file-content lfr-preview-video-content">
									<div class="lfr-preview-file-video-current-column">
										<div id="<portlet:namespace />previewFileContent"></div>
									</div>
								</div>
							</div>

							<liferay-util:include page="/html/portlet/document_library/player.jsp" />
						</c:when>
						<c:otherwise>
							<div class="lfr-preview-file" id="<portlet:namespace />previewFile">
								<div class="lfr-preview-file-content" id="<portlet:namespace />previewFileContent">
									<div class="lfr-preview-file-image-current-column">
										<div class="lfr-preview-file-image-container">
											<img class="lfr-preview-file-image-current" id="<portlet:namespace />previewFileImage" src="<%= previewFileURL + "1" %>" />
										</div>
										<span class="hide lfr-preview-file-actions" id="<portlet:namespace />previewFileActions">
											<span class="lfr-preview-file-toolbar" id="<portlet:namespace />previewToolbar"></span>

											<span class="lfr-preview-file-info">
												<span class="lfr-preview-file-index" id="<portlet:namespace />previewFileIndex">1</span> of <span class="lfr-preview-file-count"><%= previewFileCount %></span>
											</span>
										</span>
									</div>

									<div class="lfr-preview-file-images" id="<portlet:namespace />previewImagesContent">
										<div class="lfr-preview-file-images-content"></div>
									</div>
								</div>
							</div>

							<aui:script use="liferay-preview">
								new Liferay.Preview(
									{
										actionContent: '#<portlet:namespace />previewFileActions',
										baseImageURL: '<%= previewFileURL %>',
										boundingBox: '#<portlet:namespace />previewFile',
										contentBox: '#<portlet:namespace />previewFileContent',
										currentPreviewImage: '#<portlet:namespace />previewFileImage',
										imageListContent: '#<portlet:namespace />previewImagesContent',
										maxIndex: <%= previewFileCount %>,
										previewFileIndexNode: '#<portlet:namespace />previewFileIndex',
										toolbar: '#<portlet:namespace />previewToolbar'
									}
								).render();
							</aui:script>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>