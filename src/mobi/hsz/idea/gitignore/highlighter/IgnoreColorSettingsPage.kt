/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 hsz Jakub Chrzanowski <jakub@hsz.mobi>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mobi.hsz.idea.gitignore.highlighter

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import mobi.hsz.idea.gitignore.IgnoreBundle
import mobi.hsz.idea.gitignore.util.Resources
import org.jetbrains.annotations.NonNls
import javax.swing.Icon

/**
 * [ColorSettingsPage] that allows to modify color scheme.
 *
 * @author Jakub Chrzanowski <jakub@hsz.mobi>
 * @since 0.1
 */
class IgnoreColorSettingsPage : ColorSettingsPage {
    /**
     * Returns the icon for the page, shown in the dialog tab.
     *
     * @return the icon for the page, or null if the page does not have a custom icon.
     */
    override fun getIcon(): Icon? = null

    /**
     * Returns the syntax highlighter which is used to highlight the text shown in the preview
     * pane of the page.
     *
     * @return the syntax highlighter instance.
     */
    override fun getHighlighter(): SyntaxHighlighter = IgnoreHighlighter(null, null)

    /**
     * Returns the text shown in the preview pane.
     *
     * @return demo text
     */
    override fun getDemoText(): String = SAMPLE_GITIGNORE

    /**
     * Returns the mapping from special tag names surrounding the regions to be highlighted
     * in the preview text.
     *
     * @return `null`
     */
    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null

    /**
     * Returns the list of descriptors specifying the [TextAttributesKey] instances
     * for which colors are specified in the page. For such attribute keys, the user can choose
     * all highlighting attributes (font type, background color, foreground color, error stripe color and
     * effects).
     *
     * @return the list of attribute descriptors.
     */
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    /**
     * Returns the list of descriptors specifying the [com.intellij.openapi.editor.colors.ColorKey]
     * instances for which colors are specified in the page. For such color keys, the user can
     * choose only the background or foreground color.
     *
     * @return the list of color descriptors.
     */
    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    /**
     * Returns the title of the page, shown as text in the dialog tab.
     *
     * @return the title of the custom page.
     */
    override fun getDisplayName(): String = DISPLAY_NAME

    companion object {
        /** The path to the sample .gitignore file.  */
        @NonNls
        private val SAMPLE_GITIGNORE_PATH = "/sample.gitignore"

        /** Display name for Color Settings Page.  */
        @NonNls
        private val DISPLAY_NAME = IgnoreBundle.message("ignore.colorSettings.displayName")

        /**
         * The sample .gitignore document shown in the colors settings dialog.
         *
         * @see .loadSampleGitignore
         */
        private val SAMPLE_GITIGNORE = loadSampleGitignore()

        /** Attributes descriptor list.  */
        private val DESCRIPTORS = arrayOf(
                AttributesDescriptor(IgnoreBundle.message("highlighter.header"), IgnoreHighlighterColors.HEADER),
                AttributesDescriptor(IgnoreBundle.message("highlighter.section"), IgnoreHighlighterColors.SECTION),
                AttributesDescriptor(IgnoreBundle.message("highlighter.comment"), IgnoreHighlighterColors.COMMENT),
                AttributesDescriptor(IgnoreBundle.message("highlighter.negation"), IgnoreHighlighterColors.NEGATION),
                AttributesDescriptor(IgnoreBundle.message("highlighter.brackets"), IgnoreHighlighterColors.BRACKET),
                AttributesDescriptor(IgnoreBundle.message("highlighter.slash"), IgnoreHighlighterColors.SLASH),
                AttributesDescriptor(IgnoreBundle.message("highlighter.syntax"), IgnoreHighlighterColors.SYNTAX),
                AttributesDescriptor(IgnoreBundle.message("highlighter.value"), IgnoreHighlighterColors.VALUE)
        )

        /**
         * Loads sample .gitignore file
         *
         * @return the text loaded from [.SAMPLE_GITIGNORE_PATH]
         */
        private fun loadSampleGitignore(): String = Resources.getResourceContent(SAMPLE_GITIGNORE_PATH)!!
    }
}
