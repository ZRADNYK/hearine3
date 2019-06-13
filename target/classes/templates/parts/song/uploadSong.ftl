<#include "../security.ftl">
<#import "../common.ftl" as c>

<@c.page>
    <body>
    <noscript>
        <h2>Sorry! Your browser doesn't support Javascript</h2>
    </noscript>
    <div class="upload-container">
        <div class="upload-header">
            <h2>Spring Boot File Upload / Download Rest API Example</h2>
        </div>
        <div class="upload-content">
            <div class="single-upload">
                <h3>Upload Single File</h3>
                <form method="post" id="singleUploadForm" name="singleUploadForm">
                    <input id="singleFileUploadInput" type="file" name="file" class="file-input" required />

                    <button type="submit" class="primary submit-btn">Submit</button>
                </form>
                <div class="upload-response">

                    <div id="singleFileUploadError"></div>
                    <div id="singleFileUploadSuccess"></div>
                </div>
            </div>
            <div class="multiple-upload">
                <h3>Upload Multiple Files</h3>
                <form method="post"  id="multipleUploadForm" name="multipleUploadForm">
                    <input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />

                    <button type="submit" class="primary submit-btn">Submit</button>
                </form>
                <div class="upload-response">

 <!--                   <div id="multipleFileUploadError"></div> -->
                    <div id="multipleFileUploadSuccess"></div>
                </div>
            </div>
        </div>
    </div>
    <script src="/static/js/main.js" ></script>
    </body>


</@c.page>